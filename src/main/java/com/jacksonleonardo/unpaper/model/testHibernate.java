package com.jacksonleonardo.unpaper.model;

import com.jacksonleonardo.unpaper.model.entities.*;
import com.jacksonleonardo.unpaper.model.enumerators.EOperationType;
import com.jacksonleonardo.unpaper.model.enumerators.ERepetitionFrequency;
import com.jacksonleonardo.unpaper.model.repositories.FormOfPaymentRepository;
import com.jacksonleonardo.unpaper.model.repositories.MovementCategoryRepository;
import com.jacksonleonardo.unpaper.model.repositories.UserRepository;
import com.jacksonleonardo.unpaper.model.services.SessionService;
import com.jacksonleonardo.unpaper.model.valueObjects.Email;
import com.jacksonleonardo.unpaper.model.valueObjects.IEmail;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.*;

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
        IMovementCategory mc1 = MovementCategory.createPublicCategory("Rendimentos", uri);
        IMovementCategory mc2 = MovementCategory.createPublicCategory("Despesas Gerais Familiares", uri);
        MovementCategoryRepository.getInstance().add(MovementCategory.createPublicCategory("Restauração", uri));
        MovementCategoryRepository.getInstance().add(MovementCategory.createPublicCategory("Educação", uri));
        MovementCategoryRepository.getInstance().add(mc1);
        MovementCategoryRepository.getInstance().add(MovementCategory.createPublicCategory("Passes Mensais", uri));
        MovementCategoryRepository.getInstance().add(MovementCategory.createPublicCategory("Estacionamento", uri));
        MovementCategoryRepository.getInstance().add(MovementCategory.createPublicCategory("Saúde", uri));
        MovementCategoryRepository.getInstance().add(MovementCategory.createPublicCategory("Seguros", uri));
        MovementCategoryRepository.getInstance().add(MovementCategory.createPublicCategory("Impostos", uri));
        MovementCategoryRepository.getInstance().add(MovementCategory.createPublicCategory("Tarifas Bancárias", uri));
        MovementCategoryRepository.getInstance().add(mc2);

        // Register payment methods
        IFormOfPayment formOfPayment1 = new FormOfPayment("MB Way");
        IFormOfPayment formOfPayment2 = new FormOfPayment("Dinheiro");
        IFormOfPayment formOfPayment3 = new FormOfPayment("Cheque");
        IFormOfPayment formOfPayment4 = new FormOfPayment("Transferência Bancária");

        FormOfPaymentRepository.defaultFormOfPaymentRepository().add(formOfPayment1);
        FormOfPaymentRepository.defaultFormOfPaymentRepository().add(formOfPayment2);
        FormOfPaymentRepository.defaultFormOfPaymentRepository().add(formOfPayment3);
        FormOfPaymentRepository.defaultFormOfPaymentRepository().add(formOfPayment4);


        IWallet w1 = new Wallet("Millenium", "Conta a Ordem no Millenium BCP.",
                Currency.getInstance(Locale.getDefault()), Arrays.asList(formOfPayment1, formOfPayment2), new Payee("Millenium"));
        IWallet w2 = new Wallet("Santander", "Conta salário no Santander.",
                Currency.getInstance(Locale.getDefault()), Arrays.asList(formOfPayment1, formOfPayment2, formOfPayment4), new Payee("Santander"));
        IWallet w3 = new Wallet("Crédito Agrícola", "Poupança para reforma.",
                Currency.getInstance(Locale.getDefault()), Arrays.asList(formOfPayment2, formOfPayment4), new Payee("Crédito Agrícola"));

        Huffman.addWallet(w1);
        Huffman.addWallet(w2);
        Huffman.addWallet(w3);

        IPayee payee1 = new Payee("IPVC");
        IPayee payee2 = new Payee("Continente");
        IPayee payee3 = new Payee("Pingo Doce");
        IPayee payee4 = new Payee("Microsoft");
        IPayee payee5 = new Payee("MIT");
        IPayee payee6 = new Payee("EDP");

        Huffman.addPayee(payee1);
        Huffman.addPayee(payee2);
        Huffman.addPayee(payee3);
        Huffman.addPayee(payee4);
        Huffman.addPayee(payee5);
        Huffman.addPayee(payee6);

        IMovementCategory category1 = new MovementCategory("Fast Food");
        IMovementCategory category2 = new MovementCategory("Ginásio");
        IMovementCategory category4 = new MovementCategory("Criptomoeda");
        IMovementCategory category5 = new MovementCategory("Investimentos");

        Huffman.addCategory(category1);
        Huffman.addCategory(category2);
        Huffman.addCategory(category4);
        Huffman.addCategory(category5);

        IMovement movement1 = new Movement("Salário", new BigDecimal("3800.00"), LocalDate.parse("2021-01-01"), formOfPayment4, payee4, mc1, EOperationType.CREDIT, ERepetitionFrequency.MONTHLY, null);

        w2.addMovement(movement1);
        movement1.accomplish(LocalDate.parse("2021-01-02"));
        w2.updateMovement(movement1);
        Huffman.updateWallet(w2);

        IMovement movement2 = new Movement("Conta de luz", new BigDecimal("153.89"), LocalDate.parse("2021-06-03"), formOfPayment1,payee6, mc2,EOperationType.DEBIT );
        w2.addMovement(movement2);
        movement2.accomplish(LocalDate.parse("2021-06-05"));
        w2.updateMovement(movement2);
        Huffman.updateWallet(w2);

        IMovement movement3 = new Movement("Bolsa Doutoramento", new BigDecimal("6250.89"), LocalDate.parse("2021-06-03"), formOfPayment4,payee5, mc1, EOperationType.CREDIT );

        w2.addMovement(movement3);
        movement3.accomplish(LocalDate.parse("2021-06-04"));
        w2.updateMovement(movement2);
        Huffman.updateWallet(w2);

        UserRepository.getInstance().update(Huffman);

    }
}
