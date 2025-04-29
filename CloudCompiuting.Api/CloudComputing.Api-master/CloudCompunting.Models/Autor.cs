namespace CloudComputing.Models
{
    public class Autor
    {
        public int Id { get; set; }             // PK
        public string Name { get; set; }
        public string LastName { get; set; }
        public string Biography { get; set; }

        // FK
        public int PaisId { get; set; }         // FK

        // navigation properties
        public Pais? Pais { get; set; }         // navigation property
        public List<Libro>? Libros { get; set; } // navigation property
    }
}
