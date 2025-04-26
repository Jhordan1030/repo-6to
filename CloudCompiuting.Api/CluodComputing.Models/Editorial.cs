using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CluodComputing.Models
{
    public class Editorial
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Address { get; set; }
        public string Phone { get; set; }
        public string Email { get; set; }

        //FK
        public int PaisId {  get; set; }

        //navigation properties
        public Pais? Pais { get; set; }
        public List<Libro>? Libros { get; set; }
    }
}
