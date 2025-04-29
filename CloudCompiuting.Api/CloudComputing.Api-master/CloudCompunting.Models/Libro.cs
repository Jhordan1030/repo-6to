using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CloudComputing.Models
{
    public class Libro
    {
        public int Id { get; set; }             // PK
        public string Title { get; set; }
        public string ISBN { get; set; }
        public DateTime PublicationDate { get; set; }
        public int Pages { get; set; }
        public string Language { get; set; }
        public string Description { get; set; }

        // FK
        public int AutorId { get; set; }         // FK
        public int EditorialId { get; set; }     // FK

        //navigation properties
        public Autor? Autor { get; set; }         // navigation property
        public Editorial? Editorial { get; set; }     // navigation property
    }
}
