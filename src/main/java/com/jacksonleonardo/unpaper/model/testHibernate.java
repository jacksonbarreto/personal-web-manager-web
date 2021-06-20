package com.jacksonleonardo.unpaper.model;

import com.jacksonleonardo.unpaper.model.entities.*;
import com.jacksonleonardo.unpaper.model.repositories.FormOfPaymentRepository;
import com.jacksonleonardo.unpaper.model.repositories.MovementCategoryRepository;
import com.jacksonleonardo.unpaper.model.repositories.UserRepository;
import com.jacksonleonardo.unpaper.model.services.SessionService;
import com.jacksonleonardo.unpaper.model.valueObjects.Email;
import com.jacksonleonardo.unpaper.model.valueObjects.IEmail;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Currency;
import java.util.Locale;

import static com.jacksonleonardo.unpaper.model.enumerators.ERole.ADMIN;
import static com.jacksonleonardo.unpaper.model.enumerators.ERole.SIMPLE;
import static com.jacksonleonardo.unpaper.model.enumerators.EUserState.ACTIVE;

public class testHibernate {
    public static void main(String[] args) {

        ICredential credential1 = new Credential("admin", "admin123");
        IEmail email = new Email("admin@admin.com");
        String name = "Gestor Genérico";
        IUser user = new User(name, credential1,
                Collections.singletonList(ACTIVE),
                Collections.singletonList(ADMIN),
                email);
        UserRepository.getInstance().add(user);

        SessionService.addUserInSession(user);

        // Registration of system administrators
        UserRepository.getInstance().add(new User("Alan Turing", new Credential("turing", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(ADMIN),
                new Email("turing@ipvc.pt")));

        UserRepository.getInstance().add(new User("Alonzo Church", new Credential("church", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(ADMIN),
                new Email("church@ipvc.pt")));

        UserRepository.getInstance().add(new User("John von Neumann", new Credential("neumann", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(ADMIN),
                new Email("neumann@ipvc.pt")));

        UserRepository.getInstance().add(new User("Ada Lovelace", new Credential("lovelace", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(ADMIN),
                new Email("lovelace@ipvc.pt")));

        UserRepository.getInstance().add(new User("Leonhard Euler", new Credential("euler", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(ADMIN),
                new Email("euler@ipvc.pt")));

        // Registration of common users
        IUser Huffman = new User("David Albert Huffman", new Credential("huffman", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(SIMPLE),
                new Email("huffman@ipvc.pt"));

        IFormOfPayment formOfPayment1 = new FormOfPayment("MB Way Express");
        IPayee payeeFormat = new Payee("Wallet");
        IWallet w1 = new Wallet("Wallet", "Save money for Disney",
                Currency.getInstance(Locale.getDefault()), Collections.singleton(formOfPayment1), payeeFormat);
        Huffman.addWallet(w1);


        UserRepository.getInstance().add(Huffman);

        UserRepository.getInstance().add(new User("Melinda Gates", new Credential("melinda", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(SIMPLE),
                new Email("melinda@ipvc.pt")));

        UserRepository.getInstance().add(new User("William Rowan Hamilton", new Credential("hamilton", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(SIMPLE),
                new Email("hamilton@ipvc.pt")));

        UserRepository.getInstance().add(new User("Charles Babbage", new Credential("babbage", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(SIMPLE),
                new Email("babbage@ipvc.pt")));

        UserRepository.getInstance().add(new User("Tim Berners-Lee", new Credential("bernersLee", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(SIMPLE),
                new Email("bernersLee@ipvc.pt")));

        UserRepository.getInstance().add(new User("Linus Torvalds", new Credential("torvalds", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(SIMPLE),
                new Email("torvalds@ipvc.pt")));

        UserRepository.getInstance().add(new User("Richard Matthew Stallman", new Credential("stallman", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(SIMPLE),
                new Email("stallman@ipvc.pt")));

        UserRepository.getInstance().add(new User("Douglas Engelbart", new Credential("engelbart", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(SIMPLE),
                new Email("engelbart@ipvc.pt")));

        UserRepository.getInstance().add(new User("Dennis Ritchie", new Credential("ritchie", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(SIMPLE),
                new Email("ritchie@ipvc.pt")));

        UserRepository.getInstance().add(new User("Robert Cecil Martin", new Credential("martin", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(SIMPLE),
                new Email("martin@ipvc.pt")));

        UserRepository.getInstance().add(new User("Martin Fowler", new Credential("fowler", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(SIMPLE),
                new Email("fowler@ipvc.pt")));

        UserRepository.getInstance().add(new User("Erich Gamma", new Credential("gamma", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(SIMPLE),
                new Email("gamma@ipvc.pt")));

        UserRepository.getInstance().add(new User("Richard Helm", new Credential("helm", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(SIMPLE),
                new Email("helm@ipvc.pt")));

        UserRepository.getInstance().add(new User("Ralph Johnson", new Credential("johnson", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(SIMPLE),
                new Email("johnson@ipvc.pt")));

        UserRepository.getInstance().add(new User("Grady Booch", new Credential("booch", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(SIMPLE),
                new Email("booch@ipvc.pt")));

        UserRepository.getInstance().add(new User("Ivar Jacobson", new Credential("jacobson", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(SIMPLE),
                new Email("jacobson@ipvc.pt")));

        UserRepository.getInstance().add(new User("James Rumbaugh", new Credential("rumbaugh", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(SIMPLE),
                new Email("rumbaugh@ipvc.pt")));

        UserRepository.getInstance().add(new User("Ian Sommerville", new Credential("sommerville", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(SIMPLE),
                new Email("sommerville@ipvc.pt")));

        UserRepository.getInstance().add(new User("Roger Pressman", new Credential("pressman", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(SIMPLE),
                new Email("pressman@ipvc.pt")));

        UserRepository.getInstance().add(new User("Andrew Stuart Tanenbaum", new Credential("tanenbaum", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(SIMPLE),
                new Email("tanenbaum@ipvc.pt")));

        UserRepository.getInstance().add(new User("Jim Kurose", new Credential("kurose", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(SIMPLE),
                new Email("kurose@ipvc.pt")));

        UserRepository.getInstance().add(new User("Barbara Liskov", new Credential("liskov", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(SIMPLE),
                new Email("liskov@ipvc.pt")));

        UserRepository.getInstance().add(new User("Michael C. Feathers", new Credential("feathers", "admin123"),
                Collections.singletonList(ACTIVE),
                Collections.singletonList(SIMPLE),
                new Email("feathers@ipvc.pt")));

        // records categories
        URI uri = null;
        try {
            uri = new URI("icon.png");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        MovementCategoryRepository.getInstance().add(MovementCategory.createPublicCategory("Restauração", uri));
        MovementCategoryRepository.getInstance().add(MovementCategory.createPublicCategory("Educação", uri));
        MovementCategoryRepository.getInstance().add(MovementCategory.createPublicCategory("Passes Mensais", uri));
        MovementCategoryRepository.getInstance().add(MovementCategory.createPublicCategory("Estacionamento", uri));
        MovementCategoryRepository.getInstance().add(MovementCategory.createPublicCategory("Saúde", uri));
        MovementCategoryRepository.getInstance().add(MovementCategory.createPublicCategory("Seguros", uri));
        MovementCategoryRepository.getInstance().add(MovementCategory.createPublicCategory("Impostos", uri));
        MovementCategoryRepository.getInstance().add(MovementCategory.createPublicCategory("Tarifas Bancárias", uri));
        MovementCategoryRepository.getInstance().add(MovementCategory.createPublicCategory("Despesas Gerais Familiares", uri));

        // Register payment methods

        FormOfPaymentRepository.defaultFormOfPaymentRepository().add(new FormOfPayment("MB Way"));
        FormOfPaymentRepository.defaultFormOfPaymentRepository().add(new FormOfPayment("Dinheiro"));
        FormOfPaymentRepository.defaultFormOfPaymentRepository().add(new FormOfPayment("Cheque"));
        FormOfPaymentRepository.defaultFormOfPaymentRepository().add(new FormOfPayment("Transferência Bancária"));
    }
}
