using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CluodComputing.Models
{
    public class Libro
    {
        public int Id { get; set; }
        public string Title { get; set; }
        
        public DateTime PublicationDate { get; set; }
        public int Pages {  get; set; }
        public string Language { get; set; }
        public string Description { get; set; }

        //FK
        public int AutorId {  get; set; }
        public int EditorialId {  get; set; }

        //navigation properties
        public Autor? Autor { get; set; }
        public Editorial? Editorial { get; set; }
    }
}
