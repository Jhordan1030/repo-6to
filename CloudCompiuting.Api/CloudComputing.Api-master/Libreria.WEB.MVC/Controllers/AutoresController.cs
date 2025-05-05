using CloudComputing.API.Consumer;
using CloudComputing.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;

namespace CloudComputing.WEB.MVC.Controllers
{
    public class AutoresController : Controller
    {
        // GET: AutoresController
        public ActionResult Index()
        {
            var data = Crud<Autor>.GetAll().Result;
            return View(data);
        }

        // GET: AutoresController/Details/5
        public ActionResult Details(int id)
        {
            var data = Crud<Autor>.Get(id).Result;
            return View(data);
        }

        // GET: AutoresController/Create
        public ActionResult Create()
        {
            ViewBag.ListaPaises = ListaPaises();
            return View();
        }

        // POST: AutoresController/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(Autor autor)
        {
            try
            {
                Crud<Autor>.Create(autor).Wait();
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                ModelState.AddModelError("", "Error al crear el autor");
                return View(autor);
            }
        }
        private List<SelectListItem> ListaPaises()
        {
            var paises = Crud<Pais>.GetAll().Result;
            var lista = paises.Select(x => new SelectListItem
            {
                Value = x.Id.ToString(),
                Text = x.Name,

            }).ToList();
            return lista;
        }
        // GET: AutoresController/Edit/5
        public ActionResult Edit(int id)
        {
            ViewBag.ListaPaises = ListaPaises();
            var data = Crud<Autor>.Get(id).Result;
            return View(data);
        }

        // POST: AutoresController/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(int id, Autor autor)
        {
            try
            {
                Crud<Autor>.Update(id, autor).Wait();
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                ModelState.AddModelError("", "Error al editar el autor");
                return View(autor);
            }
        }

        // GET: AutoresController/Delete/5
        public ActionResult Delete(int id)
        {
            var data = Crud<Autor>.Get(id).Result;
            return View(data);
        }

        // POST: AutoresController/Delete/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Delete(int id, Autor autor)
        {
            try
            {
                Crud<Autor>.Delete(id).Wait();
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                ModelState.AddModelError("", "Error al eliminar el autor");
                return View(autor);
            }
        }
    }
}
