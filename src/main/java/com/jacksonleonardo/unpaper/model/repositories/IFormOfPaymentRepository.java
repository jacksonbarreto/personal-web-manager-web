package com.jacksonleonardo.unpaper.model.repositories;

import com.jacksonleonardo.unpaper.model.entities.IFormOfPayment;

import java.util.Set;

public interface IFormOfPaymentRepository extends IRepository<IFormOfPayment> {

    /**
     * Returns all movement categories, that is, public and user categories.
     *
     * @return all movement categories, that is, public and user categories.
     */
    Set<IFormOfPayment> getAll();
}
