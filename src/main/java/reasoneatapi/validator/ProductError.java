package reasoneatapi.validator;

public enum ProductError {
    NAME_NOT_NULL(
            "name.not_null",
            "name",
            "Le libellé est obligatoire"
    ),
    IMAGE_NOT_NULL(
            "image.not_null",
            "image",
            "Le lien de l'image est obligatoire"
    ),
    ALREADY_EXIST(
            "product.exist",
            "",
            "Le produit existe déjà"
    ),
    NAME_TOO_SHORT(
            "name.too_short",
            "name",
            "Le libellé doit contenir au moins 3 caractères"
    ),
    NAME_TOO_LONG(
            "name.too_long",
            "name",
            "Le libellé doit contenir moins de 50 caractères"
    ),
    IMAGE_TOO_LONG(
            "image.too_long",
            "image",
            "Le lien de l'image doit contenir moins de 255 caractères"
    ),
    HEADER_TEXT_TOO_SHORT(
            "header_text.too_short",
            "header_text",
            "Le texte de haut de page doit contenir plus de 50 caractères"
    ),
    FOOTER_TEXT_TOO_SHORT(
            "footer_text.too_short",
            "footer_text",
            "Le texte de bas de page doit contenir plus de 50 caractères"
    ),
    CATEGORY_NOT_NULL(
            "category.not_null",
            "category",
            "La catégorie est obligatoire"
    ),
    CATEGORY_INVALID(
            "category.invalid",
            "category",
            "La catégorie n'est pas valide"
    ),
    MONTHS_NOT_NULL(
            "months.not_null",
            "months",
            "Les mois sont obligatoires"
    ),
    MONTHS_TOO_SHORT(
            "months.too_short",
            "months",
            "Au moins un mois doit être renseigné"
    ),
    MONTHS_INVALID(
            "months.invalid",
            "months",
            "Les mois ne sont pas valides"
    ),
    ;

    private String code;
    private String field;
    private String message;

    ProductError(String code, String field, String message) {
        this.code = code;
        this.field = field;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getField() {
        return this.field;
    }

    public String getMessage() {
        return this.message;
    }
}
