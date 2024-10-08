package ru.shutovna.moyserf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shutovna.moyserf.model.Transaction;
import ru.shutovna.moyserf.model.TransactionType;
import ru.shutovna.moyserf.model.User;
import ru.shutovna.moyserf.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransactionService implements ITransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> getTransaction(int id) {
        return transactionRepository.findById(id);
    }

    @Override
    public Transaction createTransaction(TransactionType type, String description, long sum, User user) {
        Transaction transaction = new Transaction();
        transaction.setType(type);
        transaction.setDescription(description);
        transaction.setSum(sum);
        transaction.setCompleted(false);
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setUser(user);
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getMyTransactions() {
        User currentUser = userService.getCurrentUser();
        return transactionRepository.findAllByUserOrderByCreatedAtDesc(currentUser);
    }
}
