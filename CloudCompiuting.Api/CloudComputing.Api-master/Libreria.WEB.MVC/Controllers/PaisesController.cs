using CloudComputing.API.Consumer;
using CloudComputing.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace CloudComputing.WEB.MVC.Controllers
{
    public class PaisesController : Controller
    {
        // GET: PaisesController
        public ActionResult Index()
        {
            var data = Crud<Pais>.GetAll().Result;
            return View(data);
        }

        // GET: PaisesController/Details/5
        public ActionResult Details(int id)
        {
            var data = Crud<Pais>.Get(id).Result;
            return View(data);
        }

        // GET: PaisesController/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: PaisesController/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(Pais pais)
        {
            try
            {
                Crud<Pais>.Create(pais).Wait();
                return RedirectToAction(nameof(Index));
            }
            catch( Exception ex)
            {
                ViewBag.Error = ex.Message;
                return View(pais);
            }
        }

        // GET: PaisesController/Edit/5
        public ActionResult Edit(int id)
        {
            var data = Crud<Pais>.Get(id).Result;
            return View(data);
        }

        // POST: PaisesController/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(int id, Pais pais)
        {
            try
            {
                Crud<Pais>.Update(id, pais).Wait();
                return RedirectToAction(nameof(Index));
            }
            catch(Exception ex)
            {
                ViewBag.Error = ex.Message;
                return View(pais);
            }
        }

        // GET: PaisesController/Delete/5
        public ActionResult Delete(int id)
        {
            var data = Crud<Pais>.Get(id).Result;
            return View(data);
        }

        // POST: PaisesController/Delete/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Delete(int id, IFormCollection collection)
        {
            try
            {
                Crud<Pais>.Delete(id).Wait();
                return RedirectToAction(nameof(Index));
            }
            catch(Exception ex)
            {
                ViewBag.Error = ex.Message;
                return View();
            }
        }
    }
}
