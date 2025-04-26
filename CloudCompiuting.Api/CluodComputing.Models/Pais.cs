using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CluodComputing.Models
{
    public class Pais
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Capital { get; set; }
        public string Region { get; set; }

        //navigation properties
        public List<Autor>? Autores {get; set; }
        public List<Editorial>? Editorials {get; set; }


    }
}
