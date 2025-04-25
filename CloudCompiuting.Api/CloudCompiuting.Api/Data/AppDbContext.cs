using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using CluodComputing.Models;

    public class AppDbContext : DbContext
    {
        public AppDbContext (DbContextOptions<AppDbContext> options)
            : base(options)
        {
        }

        public DbSet<CluodComputing.Models.Autor> Autores { get; set; } = default!;

        public DbSet<CluodComputing.Models.Editorial> Editoriales { get; set; } = default!;

        public DbSet<CluodComputing.Models.Libro> Libros { get; set; } = default!;

        public DbSet<CluodComputing.Models.Pais> Paises { get; set; } = default!;
    }
