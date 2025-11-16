package entities;

public class Libro {

    private long idLibro;
    private boolean eliminado;
    private String titulo;
    private String autor;
    private String editorial;
    private int anioEdicion;
    private FichaBibliografica fichaBibliografica;

    public Libro() {
    }

    public Libro(long idLibro, boolean eliminado, String titulo, String autor, String editorial, int anioEdicion) {
        this.idLibro = idLibro;
        this.eliminado = eliminado;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anioEdicion = anioEdicion;

    }

    public long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(long idLibro) {
        this.idLibro = idLibro;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAnioEdicion() {
        return anioEdicion;
    }

    public void setAnioEdicion(int anioEdicion) {
        this.anioEdicion = anioEdicion;
    }

    public FichaBibliografica getFicha() {
        return fichaBibliografica;
    }

    public void setFicha(FichaBibliografica ficha) {
        this.fichaBibliografica = ficha;
    }

    @Override
    public String toString() {
        return "\n=== Libro ==="
                + "\nID: " + idLibro
                + "\nTítulo: " + titulo
                + "\nAutor: " + autor
                + "\nEditorial: " + editorial
                + "\nAño edición: " + anioEdicion
                + "\nEstado: " + (eliminado ? "Eliminado" : "Activo")
                + "\n" + (fichaBibliografica != null ? fichaBibliografica.toString() : "   Sin ficha bibliográfica")
                + "\n-----------------------------";
    }
}
