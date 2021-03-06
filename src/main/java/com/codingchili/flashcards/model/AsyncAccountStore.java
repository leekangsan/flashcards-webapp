package com.codingchili.flashcards.model;

import io.vertx.core.Future;

import java.util.Collection;

import com.codingchili.core.security.Token;

/**
 * Interface used to handle authentication and the storage of users.
 */
public interface AsyncAccountStore {
    /**
     * Authenticates the given username:password combination.
     *
     * @param username the username to authenticate as
     * @param password the password for the given user
     * @return callback
     */
    Future<Token> authenticate(String username, String password);

    /**
     * Registers a new account if not already existing.
     *
     * @param account the account to register
     * @return callback
     */
    Future<Token> register(FlashAccount account);

    /**
     * Searches for users, useful when sharing categories.
     *
     * @param username the query string, must match the beginning.
     * @return a collection of users with an username that resembles the given
     */
    Future<Collection<FlashAccount>> search(String username);

    /**
     * Gets the number of accounts in the system.
     *
     * @return callback
     */
    Future<Integer> size();

    /**
     * Sends a message to another account.
     *
     * @param receiver the receiver of the message
     * @param message  the message to send.
     * @return callback.
     */
    Future<Void> message(String receiver, AccountMessage message);

    /**
     * Retrieve messages in an users inbox.
     *
     * @param username the username to get the inbox of.
     * @return callback.
     */
    Future<Collection<AccountMessage>> inbox(String username);

    /**
     * Marks a message in the users inbox as read.
     *
     * @param username  the user
     * @param messageId the id of the message to mark as read.
     * @return callback.
     */
    Future<Collection<AccountMessage>> read(String username, String messageId);

    /**
     * Updates the password of an existing user.
     *
     * @param username    the username to change the password for.
     * @param newpassword the new password to use.
     * @return callback.
     */
    Future<Void> updatePassword(String username, String oldpass, String newpassword);
}
