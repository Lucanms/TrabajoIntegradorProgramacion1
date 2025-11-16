package entities;

public class FichaBibliografica {

    private Long idFichaBibliografica;
    private boolean eliminado;
    private String isbn;
    private String clasificacionDewey;
    private String estanteria;
    private String idioma;
    private Long idLibro;

    public FichaBibliografica() {
    }

    public FichaBibliografica(Long idFichaBibliografica, boolean eliminado, String isbn,
            String clasificacionDewey, String estanteria, String idioma, Long idLibro) {
        this.idFichaBibliografica = idFichaBibliografica;
        this.eliminado = eliminado;
        this.isbn = isbn;
        this.clasificacionDewey = clasificacionDewey;
        this.estanteria = estanteria;
        this.idioma = idioma;
        this.idLibro = idLibro;
    }

    public long getIdFichaBibliografica() {
        return idFichaBibliografica;
    }

    public void setIdFichaBibliografica(Long idFichaBibliografica) {
        this.idFichaBibliografica = idFichaBibliografica;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getClasificacionDewey() {
        return clasificacionDewey;
    }

    public void setClasificacionDewey(String clasificacionDewey) {
        this.clasificacionDewey = clasificacionDewey;
    }

    public String getEstanteria() {
        return estanteria;
    }

    public void setEstanteria(String estanteria) {
        this.estanteria = estanteria;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }

    @Override
    public String toString() {
        return "\n   --- Ficha Bibliográfica ---"
                + "\n   ID: " + idFichaBibliografica
                + "\n   ISBN: " + isbn
                + "\n   Clasificación Dewey: " + clasificacionDewey
                + "\n   Estantería: " + estanteria
                + "\n   Idioma: " + idioma
                + "\n";
    }

}
