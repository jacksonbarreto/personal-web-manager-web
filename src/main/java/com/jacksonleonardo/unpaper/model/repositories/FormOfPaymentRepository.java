package com.jacksonleonardo.unpaper.model.repositories;

import com.jacksonleonardo.unpaper.model.entities.IFormOfPayment;
import com.jacksonleonardo.unpaper.model.exceptions.ExistingFormOfPaymentException;
import com.jacksonleonardo.unpaper.model.exceptions.NullArgumentException;
import com.jacksonleonardo.unpaper.model.exceptions.UserIsNotAuthorizedForActionException;
import com.jacksonleonardo.unpaper.model.services.SessionService;
import com.jacksonleonardo.unpaper.dal.infra.IDAO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.jacksonleonardo.unpaper.model.enumerators.ERole.ADMIN;
import static com.jacksonleonardo.unpaper.model.services.PermissionService.permissionServiceDefault;
import static com.jacksonleonardo.unpaper.dal.infra.FormOfPaymentDAO.defaultFormOfPaymentDAO;

public class FormOfPaymentRepository implements IFormOfPaymentRepository {
    private final IDAO<IFormOfPayment> formOfPaymentDAO;

    public FormOfPaymentRepository(IDAO<IFormOfPayment> formOfPaymentDAO) {
        this.formOfPaymentDAO = formOfPaymentDAO;
    }

    public static IFormOfPaymentRepository defaultFormOfPaymentRepository() {
        return new FormOfPaymentRepository(defaultFormOfPaymentDAO());
    }


    @Override
    public Set<IFormOfPayment> getAll() {
        return new HashSet<>(this.formOfPaymentDAO.select("select f from FormOfPayment f where active = 1"));
    }

    @Override
    public Set<IFormOfPayment> get(Predicate<IFormOfPayment> predicate) {
        if (predicate == null)
            throw new NullArgumentException();
        return this.formOfPaymentDAO.select("select f from FormOfPayment f").stream().filter(predicate).collect(Collectors.toSet());
    }

    @Override
    public IFormOfPayment get(UUID id) {
        if (id == null)
            throw new NullArgumentException();
        return this.formOfPaymentDAO.select(id);
    }

    @Override
    public void add(IFormOfPayment element) {
        if (element == null)
            throw new NullArgumentException();
        List<IFormOfPayment> formOfPayments = this.formOfPaymentDAO.select("select f from FormOfPayment f");
        if (permissionServiceDefault().hasRole(SessionService.getCurrentUser(), ADMIN)) {
            if (!formOfPayments.contains(element) &&
                    formOfPayments.stream().noneMatch(f -> f.getName().equalsIgnoreCase(element.getName()))) {
                this.formOfPaymentDAO.create(element);
            } else
                throw new ExistingFormOfPaymentException();
        } else
            throw new UserIsNotAuthorizedForActionException();
    }

    @Override
    public void update(IFormOfPayment element) {
        if (element == null)
            throw new NullArgumentException();
        if (permissionServiceDefault().hasRole(SessionService.getCurrentUser(), ADMIN))
            this.formOfPaymentDAO.update(element);
        else
            throw new UserIsNotAuthorizedForActionException();
    }

    @Override
    public void remove(IFormOfPayment element) {
        if (element == null)
            throw new NullArgumentException();
        if (permissionServiceDefault().hasRole(SessionService.getCurrentUser(), ADMIN)) {
            element.inactivate();
            this.formOfPaymentDAO.update(element);
        } else
            throw new UserIsNotAuthorizedForActionException();
    }
}
