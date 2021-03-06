package com.codingchili.flashcards.request;

import com.codingchili.core.listener.Request;
import com.codingchili.core.listener.RequestWrapper;
import com.codingchili.core.protocol.Serializer;
import com.codingchili.flashcards.model.FlashCard;
import com.codingchili.flashcards.model.FlashCategory;

/**
 * Request for cards.
 */
public class CardRequest extends RequestWrapper {
    private FlashCategory category;

    public CardRequest(Request request) {
        super(request);
    }

    public String sender() {
        return super.token().getDomain();
    }

    public FlashCard card() {
        return Serializer.unpack(data(), FlashCard.class);
    }

    public void setCategory(FlashCategory category) {
        this.category = category;
    }

    public FlashCategory category() {
        return category;
    }

    public boolean categoryOwned() {
        return category.getOwner().equals(sender());
    }

    public boolean categorySharedWith() {
        if (category != null && category.getUsers() != null) {
            return category().getUsers().contains(sender());
        } else {
            return false;
        }
    }
}
