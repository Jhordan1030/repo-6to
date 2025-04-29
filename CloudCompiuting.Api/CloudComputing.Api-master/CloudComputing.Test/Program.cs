using CloudComputing.API.Consumer;
using CloudComputing.Models;

namespace CloudComputing.Test
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Crud<Pais>.EndPoint = "https://localhost:7046/api/Paises";
            Crud<Autor>.EndPoint = "https://localhost:7046/api/Autores";
            Crud<Editorial>.EndPoint = "https://localhost:7046/api/Editoriales";
            Crud<Libro>.EndPoint = "https://localhost:7046/api/Libros";

            var Ecuador = Crud<Pais>.Get(1).Result;
            /*
            var gabo = Crud<Autor>.Create(new Autor
            {
                Name = "Gabriel",
                LastName = "Garcia Marquez",
                Biography = "Escritor colombiano, ganador del Premio Nobel de Literatura en 1982. Su obra más conocida es 'Cien años de soledad'.",
                PaisId = 2
            }).Result;
            Console.WriteLine(gabo.Id + " - " + gabo.Name + " " + gabo.LastName + " " + gabo.Biography + " " + gabo.PaisId);
            */

            /*
            var santillana = Crud<Editorial>.Create(new Editorial
            {
                Name = "Santillana",
                Address = "Calle 123, Madrid, España",
                Phone = "123456789",
                PaisId = 1,
                Email = "editorial@santillana.com.es"
            }).Result;

            santillana.Address = "Calle 456, Cuenca, Ecuador";
            santillana.Email = "editorial@santillana.com.ec";
            
            var resultado =  Crud<Editorial>.Update(santillana.Id, santillana).Result;
            */

            //var eliminado = Crud<Pais>.Delete(3).Result;

            var Paises = Crud<Pais>.GetAll().Result;
            var Autores = Crud<Autor>.GetAll().Result;
            var Editoriales = Crud<Editorial>.GetAll().Result;
            var Libros = Crud<Libro>.GetAll().Result;

            Console.WriteLine("# Paises:" + Paises.Count);
            Console.WriteLine("# Autores:" + Autores.Count);
            Console.WriteLine("# Editoriales:" + Editoriales.Count);
            Console.WriteLine("# Libros:" + Libros.Count);

            Console.WriteLine(Ecuador.Name);
            Console.ReadLine();
        }
    }
}
