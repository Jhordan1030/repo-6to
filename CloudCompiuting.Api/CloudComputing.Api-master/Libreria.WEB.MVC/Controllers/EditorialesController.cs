using CloudComputing.API.Consumer;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using CloudComputing.Models;
using CloudComputing.API.Consumer;
using Microsoft.AspNetCore.Mvc.Rendering;

namespace CloudComputing.WEB.MVC.Controllers
{
    public class EditorialesController : Controller
    {
        // GET: EditorialesController
        public ActionResult Index()
        {
            var data = Crud<Editorial>.GetAll().Result;
            ViewBag.TotalRegistros = data.Count;
            return View(data);
        }

        // GET: EditorialesController/Details/5
        public ActionResult Details(int id)
        {
            var data = Crud<Editorial>.Get(id).Result;
            return View(data);
        }

        // GET: EditorialesController/Create
        public ActionResult Create()
        {
            ViewBag.ListaPaises = ListaPaises();

            return View();
        }

        // POST: EditorialesController/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(Editorial editorial)
        {
            try
            {
                Crud<Editorial>.Create(editorial).Wait();
                return RedirectToAction(nameof(Index));
            }
            catch(Exception ex)
            {
                ModelState.AddModelError("", ex.Message);
                return View(editorial);
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

        // GET: EditorialesController/Edit/5
        public ActionResult Edit(int id)
        {
            ViewBag.ListaPaises = ListaPaises();
            var data = Crud<Editorial>.Get(id).Result;
            return View(data);
        }

        

        // POST: EditorialesController/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(int id, Editorial editorial)
        {
            try
            {
                Crud<Editorial>.Update(id, editorial).Wait();
                return RedirectToAction(nameof(Index));
            }
            catch(Exception ex)
            {
                ModelState.AddModelError("", ex.Message);
                return View(editorial);
            }
        }

        // GET: EditorialesController/Delete/5
        public ActionResult Delete(int id)
        {
            var data = Crud<Editorial>.Get(id).Result;
            return View(data);
        }

        // POST: EditorialesController/Delete/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Delete(int id, Editorial editorial)
        {
            try
            {
                Crud<Editorial>.Delete(id).Wait();
                return RedirectToAction(nameof(Index));
            }
            catch(Exception ex)
            {
                ModelState.AddModelError("", ex.Message);
                return View(editorial);
            }
        }
    }
}
