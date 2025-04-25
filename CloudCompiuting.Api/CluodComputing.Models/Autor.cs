namespace CluodComputing.Models
{
    public class Autor
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string LastName { get; set; }
        public string Biography { get; set; }

        //FK
        public int PaisId { get; set; }
        

        //navigation properties
        public Pais? Pais { get; set; }
        public List<Libro>? Libros { get; set; }

    }
}
