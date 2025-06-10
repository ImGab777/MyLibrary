package com.gab.mylibrary.model.enuns;

public enum Genre {
    FANTASY("Fantasy", "Fantasia"),
    SCIENCE_FICTION("Science Fiction", "Ficção Científica"),
    MYSTERY("Mystery", "Mistério"),
    THRILLER("Thriller", "Suspense"),
    HORROR("Horror", "Terror"),
    ROMANCE("Romance", "Romance"),
    BIOGRAPHY("Biography", "Biografia"),
    HISTORY("History", "História"),
    POETRY("Poetry", "Poesia"),
    FICTION("Fiction", "Ficção"),
    NON_FICTION("Non-Fiction", "Não Ficção"),
    YOUNG_ADULT("Young Adult", "Jovem Adulto"),
    CHILDREN("Children", "Infantil"),
    COMIC_BOOK("Comic Book", "História em Quadrinhos"),
    MANGA("Manga", "Mangá"),
    NOVEL("Novel", "Romance Literário"), // Diferente de Romance (gênero)
    CLASSIC("Classic", "Clássico"),
    SELF_HELP("Self-Help", "Autoajuda"),
    TRAVEL("Travel", "Viagem"),
    COOKBOOK("Cookbook", "Livro de Receitas"),
    ESSAY("Essay", "Ensaio"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crime"); // Adicionei mais alguns

    private final String englishDisplayName;
    private final String portugueseDisplayName;

    Genre(String englishDisplayName, String portugueseDisplayName) {
        this.englishDisplayName = englishDisplayName;
        this.portugueseDisplayName = portugueseDisplayName;
    }

    public String getEnglishDisplayName() {
        return englishDisplayName;
    }

    public String getPortugueseDisplayName() {
        return portugueseDisplayName;
    }

    // Um método para obter o nome de exibição baseado em uma localidade
    public String getDisplayName(String locale) {
        if ("pt-BR".equalsIgnoreCase(locale)) {
            return portugueseDisplayName;
        }
        return englishDisplayName; // Padrão é inglês
    }
}


