package model.enum_;

/**
 * Представляет собой тип обращения
 */

public enum ResolutionType {
    RESOLUTION("Резолюция"),
    REFERRAL("Направление"),
    REQUEST("Запрос");

    /**
     * Поле для хранения значения константы
     */
    private final String category;


    /**
     * Конструктор для передачи значения константы в поле для хранения
     */
    ResolutionType(String category) {
        this.category = category;
    }

    /**
     * Возвращает заданное имя категории для константы
     */
    @Override
    public String toString() {
        return category;
    }


}