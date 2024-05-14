package com.microservices.cards.cards.service.impl;

import com.microservices.cards.cards.constants.CardsConstants;
import com.microservices.cards.cards.dto.CardDto;
import com.microservices.cards.cards.entity.Cards;
import com.microservices.cards.cards.exception.CardAlreadyExistsException;
import com.microservices.cards.cards.exception.ResourceNotFoundException;
import com.microservices.cards.cards.mapper.CardsMapper;
import com.microservices.cards.cards.repository.CardsRepository;
import com.microservices.cards.cards.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class CardServiceImpl implements ICardService {

    @Autowired
    CardsRepository cardsRepository;

    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> optionalCards= cardsRepository.findByMobileNumber(mobileNumber);
        if(optionalCards.isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber "+mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));
    }

    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    @Override
    public CardDto getCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).
                orElseThrow(()->new ResourceNotFoundException("Card Not Found"));
        return CardsMapper.maptoCardDto(cards,new CardDto());
    }

    @Override
    public boolean updateCard(CardDto cardDto) {
        Cards cards = cardsRepository.findByMobileNumber(cardDto.getMobileNumber())
                .orElseThrow(()->new ResourceNotFoundException("Card Not Found"));
        CardsMapper.maptoCards(cardDto,cards);
        cardsRepository.save(cards);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).
                orElseThrow(()-> new ResourceNotFoundException("Card Not Found"));
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }
}
