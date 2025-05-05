using CloudComputing.API.Consumer;
using CloudComputing.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;

namespace CloudComputing.WEB.MVC.Controllers
{
    public class LibrosController : Controller
    {
        // GET: LibrosController
        public ActionResult Index()
        {
            var data = Crud<Libro>.GetAll().Result;
            if (data != null && data.Any())
            {
                var fechaAntigua = data.Min(i => i.PublicationDate);
                ViewBag.LibroAntiguo = data.Where(i => i.PublicationDate == fechaAntigua).FirstOrDefault();
            }
            else
            {
                ViewBag.LibroAntiguo = null;
            }

                return View(data);
        }

        // GET: LibrosController/Details/5
        public ActionResult Details(int id)
        {
            var data = Crud<Libro>.Get(id).Result;
            return View(data);
        }

        // GET: LibrosController/Create
        public ActionResult Create()
        {
            ViewBag.ListaEditoriales = ListaEditoriales();
            ViewBag.ListaAutores = ListaAutores();
            return View();
        }

        // POST: LibrosController/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(Libro libro)
        {
            try
            {
                Crud<Libro>.Create(libro).Wait();
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                ModelState.AddModelError("", "Error al crear el libro");
                return View(libro);
            }
        }
        private List<SelectListItem> ListaEditoriales()
        {
            var editoriales = Crud<Editorial>.GetAll().Result;
            var lista = editoriales.Select(e => new SelectListItem
            {
                Value = e.Id.ToString(),
                Text = e.Name
            }).ToList();
            return lista;
        }

        private List<SelectListItem> ListaAutores()
        {
            var autores = Crud<Autor>.GetAll().Result;
            var lista = autores.Select(a => new SelectListItem
            {
                Value = a.Id.ToString(),
                Text = $"{a.LastName} {a.Name}"
            }).ToList();
            return lista;
        }

        // GET: LibrosController/Edit/5
        public ActionResult Edit(int id)
        {
            ViewBag.ListaEditoriales = ListaEditoriales();
            ViewBag.ListaAutores = ListaAutores();
            var data = Crud<Libro>.Get(id).Result;
            return View(data);
        }


        // POST: LibrosController/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(int id, Libro libro)
        {
            try
            {
                Crud<Libro>.Update(id, libro).Wait();
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                ModelState.AddModelError("", "Error al editar el libro");
                return View(libro);
            }
        }

        // GET: LibrosController/Delete/5
        public ActionResult Delete(int id)
        {
            var data = Crud<Libro>.Get(id).Result;
            return View(data);
        }

        // POST: LibrosController/Delete/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Delete(int id, Libro libro)
        {
            try
            {
                Crud<Libro>.Delete(id).Wait();
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                ModelState.AddModelError("", "Error al eliminar el libro");
                return View(libro);
            }
        }
    }
}
