using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CloudComputing.Models
{
    public class Editorial
    {
        public int Id { get; set; }             // PK
        public string Name { get; set; }
        public string Address { get; set; }
        public string Phone { get; set; }
        public string Email { get; set; }

        // FK
        public int PaisId { get; set; }         // FK

        // navigation properties
        public Pais? Pais { get; set; }         // navigation property
        public List<Libro>? Libros { get; set; } // navigation property
    }
}
