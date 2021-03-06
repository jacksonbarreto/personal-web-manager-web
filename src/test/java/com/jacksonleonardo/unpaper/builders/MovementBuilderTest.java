package com.jacksonleonardo.unpaper.builders;

import com.jacksonleonardo.unpaper.model.builders.IMovementBuilder;
import com.jacksonleonardo.unpaper.model.entities.*;
import com.jacksonleonardo.unpaper.model.valueObjects.Attachment;
import com.jacksonleonardo.unpaper.model.valueObjects.IAttachment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

import static com.jacksonleonardo.unpaper.model.enumerators.EOperationType.CREDIT;
import static com.jacksonleonardo.unpaper.model.enumerators.ERepetitionFrequency.NONE;
import static com.jacksonleonardo.unpaper.model.enumerators.ERepetitionFrequency.YEARLY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovementBuilderTest {
    String name = "Christmas shopping";
    String amount = "150.90";
    String dueDateText = "10-02-1985";
    LocalDate dueDate = LocalDate.of(1985, Month.FEBRUARY, 10);
    IFormOfPayment formOfPayment = new FormOfPayment("Transfer");
    IPayee payee = new Payee("Continent");
    URI uri1 = new URI("/rest.png");
    IMovementCategory category = new MovementCategory("Education",uri1);
    IAttachment attachment = new Attachment("www.college.pt");
    IMovementBuilder obj1 = IMovementBuilder.makeMovement(name, amount, dueDateText, formOfPayment, payee, category, CREDIT);
    IMovementBuilder obj2 = IMovementBuilder.makeMovement(name, amount, LocalDate.now(), formOfPayment, payee, category, CREDIT);
    IMovement movement = obj1.build();

    public MovementBuilderTest() throws URISyntaxException {
    }

    @Test
    public void shouldHaveCorrectName() {
        assertEquals(name, movement.getName());
    }

    @Test
    public void shouldHaveCorrectAmount() {
        assertEquals(new BigDecimal(amount), movement.getAmount());
    }

    @Test
    public void shouldHaveCorrectDueDate() {
        assertEquals(dueDate, movement.getDueDate());
        IMovement movementWithLocalDate = obj2.build();
        assertEquals(LocalDate.now(), movementWithLocalDate.getDueDate());

    }

    @Test
    public void shouldHaveCorrectFormOfPayment() {
        assertEquals(formOfPayment, movement.getFormOfPayment());
    }

    @Test
    public void shouldHaveCorrectPayee() {
        assertEquals(payee, movement.getPayee());
    }

    @Test
    public void shouldHaveCorrectCategory() {
        assertEquals(category, movement.getCategory());
    }

    @Test
    public void shouldHaveCorrectDescription() {
        assertEquals("", movement.getDescription());
        obj1.addDescription("Added Description");
        IMovement movementWithDescription = obj1.build();
        assertEquals("Added Description", movementWithDescription.getDescription());
    }

    @Test
    public void shouldHaveCorrectFrequency() {
        assertEquals(NONE, movement.getRepetitionFrequency());
        obj1.addRepetitionFrequency(YEARLY);
        IMovement movementWithFrequency = obj1.build();
        assertEquals(YEARLY, movementWithFrequency.getRepetitionFrequency());
    }

    @Test
    public void shouldCorrectGroupID() {
        assertEquals(movement.getID(), movement.getGroupID());
        UUID groupID = UUID.randomUUID();
        obj1.addGroupID(groupID);
        IMovement movementWithGroupID = obj1.build();
        assertEquals(groupID, movementWithGroupID.getGroupID());
        assertEquals(NONE, movement.getRepetitionFrequency());
    }

    @Test
    public void shouldCorrectAttachment() {
        assertTrue(movement.getAttachments().isEmpty());
        obj1.addAttachments(attachment);
        IMovement movementWithAttachments = obj1.build();
        assertTrue(movementWithAttachments.getAttachments().contains(attachment));
        assertEquals(1, movementWithAttachments.getAttachments().size());
    }
}
