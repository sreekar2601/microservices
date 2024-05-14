package com.microservices.cards.cards.mapper;

import com.microservices.cards.cards.dto.CardDto;
import com.microservices.cards.cards.entity.Cards;

public class CardsMapper {

    public static CardDto maptoCardDto(Cards cards,CardDto cardDto){
        cardDto.setMobileNumber(cards.getMobileNumber());
        cardDto.setCardNumber(cards.getCardNumber());
        cardDto.setCardType(cards.getCardType());
        cardDto.setTotalLimit(cards.getTotalLimit());
        cardDto.setAmountUsed(cards.getAmountUsed());
        cardDto.setAvailableAmount(cards.getAvailableAmount());
        return cardDto;
    }
    public static Cards maptoCards(CardDto cardDto,Cards cards){
        cards.setMobileNumber(cardDto.getMobileNumber());
        cards.setCardNumber(cardDto.getCardNumber());
        cards.setCardType(cardDto.getCardType());
        cards.setTotalLimit(cardDto.getTotalLimit());
        cards.setAmountUsed(cardDto.getAmountUsed());
        cards.setAvailableAmount(cardDto.getAvailableAmount());
        return cards;
    }


}
