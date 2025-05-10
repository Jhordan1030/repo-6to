
    public void ejercicio(int n) {
        int t = 0, i, j = 256;
        
        while (j >= 2) {
            t++;
            j = j / 2;
        }
        
        for (i = 3; i <= n + 3; i++) {
            for (j = n; j >= 0; j--) {
                metodo1();
                metodo2();
                t--;
            }
        }
        metodo3();
    }
    metodo1() —> T(n) = n4+1
    metodo2() —> T(n) = 4n +3
    metodo3() —> T(n) = 2n^3+4
