package ru.shutovna.moyserf.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shutovna.moyserf.model.User;
import ru.shutovna.moyserf.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class StatisticsService implements IStatisticsService {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IUserService userService;

    @Override
    public long getTotalIncome() {
        long sum = getUsersTotalIncome(null).stream().mapToLong(
                objects -> ((BigDecimal) objects[1]).longValue()).sum();
        log.debug("getTotalIncome:{}", sum);
        return sum;
    }

    @Override
    public long getTotalReferalsIncome() {
        Query query = em.createNativeQuery("select sum(t.sum) \n" +
                "from transactions t\n" +
                "where t.type in('USER_EARNED_REFERAL_SITE_VIEW')");
        Object result = query.getSingleResult();
        return result == null ? 0 : ((BigDecimal) result).longValue();
    }

    @Override
    public int getAdvertisersCount() {
        return userRepository.countAdvertisers();
    }

    @Override
    public int getWorkersCount() {
        return userRepository.countWorkers();
    }

    @Override
    public List<Object[]> getUsersTotalIncome(Integer limit) {
        Query query = em.createNativeQuery(
                "select t.user_id user_id, sum(t.sum) sum\n" +
                        "from transactions t \n" +
                        "where t.type in('USER_EARNED_SITE_VIEW', 'USER_EARNED_REFERAL_SITE_VIEW')\n" +
                        "group by t.user_id\n" +
                        "order by sum desc\n" +
                        (limit == null ? "" : "limit " + limit + "\n"));
        return query.getResultList();
    }

    @Override
    public int getUserViewCount() {
        User currentUser = userService.getCurrentUser();
        return currentUser.getViews().size();
    }

    @Override
    public long getUserEarned() {
        User currentUser = userService.getCurrentUser();
        Query query = em.createNativeQuery(
                "select sum(t.sum) sum\n" +
                        "from transactions t \n" +
                        "where t.type in('USER_EARNED_SITE_VIEW', 'USER_EARNED_REFERAL_SITE_VIEW')\n" +
                        "and t.user_id = ?");
        query.setParameter(1, currentUser.getId());

        Object result = query.getSingleResult();
        return result == null ? 0 : ((BigDecimal) result).longValue();
    }

    @Override
    public long getUserEarnedByReferals() {
        User currentUser = userService.getCurrentUser();
        Query query = em.createNativeQuery(
                "select sum(t.sum) sum\n" +
                        "from transactions t \n" +
                        "where t.type in('USER_EARNED_REFERAL_SITE_VIEW')\n" +
                        "and t.user_id = ?");
        query.setParameter(1, currentUser.getId());

        Object result = query.getSingleResult();
        return result == null ? 0 : ((BigDecimal) result).longValue();
    }

    @Override
    public int getMyReferalsCount() {
        return userService.getMyReferals().size();
    }

    @Override
    public long getMyReferalsIncome() {
        User currentUser = userService.getCurrentUser();
        Query query = em.createNativeQuery(
                "select sum(t.sum) sum\n" +
                        "from transactions t\n" +
                        "where t.type in('USER_EARNED_SITE_VIEW', 'USER_EARNED_REFERAL_SITE_VIEW')\n" +
                        "and t.user_id in(select id from users where invitor_id = ?)");
        query.setParameter(1, currentUser.getId());

        Object result = query.getSingleResult();
        return result == null ? 0 : ((BigDecimal) result).longValue();
    }

    @Override
    public int getMyReferalsViewCount() {
        List<User> referals = userService.getMyReferals();
        return referals.stream().mapToInt(user -> user.getViews().size()).sum();
    }
}
