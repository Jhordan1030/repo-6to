using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using CloudComputing.Models;

    public class AppDbContext : DbContext
    {
        public AppDbContext (DbContextOptions<AppDbContext> options)
            : base(options)
        {
        }

        public DbSet<CloudComputing.Models.Autor> Autores { get; set; } = default!;

        public DbSet<CloudComputing.Models.Editorial> Editoriales { get; set; } = default!;

        public DbSet<CloudComputing.Models.Libro> Libros { get; set; } = default!;

        public DbSet<CloudComputing.Models.Pais> Paises { get; set; } = default!;
    }
