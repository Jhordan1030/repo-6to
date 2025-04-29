using CloudComputing.API.Consumer;
using CloudComputing.Models;

namespace Libreria.WEB.MVC
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var BaseUrl = "https://localhost:7046/api";

            Crud<Pais>.EndPoint = $"{BaseUrl}/Paises";
            Crud<Autor>.EndPoint = $"{BaseUrl}/Autores";
            Crud<Editorial>.EndPoint = $"{BaseUrl}/Editoriales";
            Crud<Libro>.EndPoint = $"{BaseUrl}/Libros";

            var builder = WebApplication.CreateBuilder(args);

            // Add services to the container.
            builder.Services.AddControllersWithViews();

            var app = builder.Build();

            // Configure the HTTP request pipeline.
            if (!app.Environment.IsDevelopment())
            {
                app.UseExceptionHandler("/Home/Error");
                // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
                app.UseHsts();
            }

            app.UseHttpsRedirection();
            app.UseStaticFiles();

            app.UseRouting();

            app.UseAuthorization();

            app.MapControllerRoute(
                name: "default",
                pattern: "{controller=Home}/{action=Index}/{id?}");

            app.Run();
        }
    }
}
