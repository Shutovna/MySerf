package ru.shutovna.moyserf.service;

public class SimplePricingStrategy implements IPricingStrategy{
    @Override
    public long getSiteViewPrice() {
        return 24;
    }
}
