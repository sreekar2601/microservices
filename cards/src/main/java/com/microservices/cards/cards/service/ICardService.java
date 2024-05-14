package com.microservices.cards.cards.service;

import com.microservices.cards.cards.dto.CardDto;

public interface ICardService {

    void createCard(String mobileNumber);

    CardDto getCard(String mobileNumber);

    boolean updateCard(CardDto cardDto);

    boolean deleteCard(String mobileNumber);

}
