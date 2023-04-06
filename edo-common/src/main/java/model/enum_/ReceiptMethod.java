package model.enum_;

/**
 * Enum "ReceiptMethod" - способ получения обращения (На бумаге, Через электронную почту, Лично в приемной, По телефону)
 */
public enum ReceiptMethod {

    ON_THE_PAPER("На бумаге"),
    VIA_EMAIL("Через электронную почту"),
    IN_PERSON_AT_THE_RECEPTION("Лично в приемной"),
    BY_PHONE("По телефону");

    /**
     * "receiptMethod" - поле, созданное для того, чтобы можно было перопределить toString()
     */
    private final String receiptMethod;

    /**
     * Конструктор, чтобы Enum был на русском языке
     */
    ReceiptMethod(String receiptMethod) {
        this.receiptMethod = receiptMethod;
    }
}
