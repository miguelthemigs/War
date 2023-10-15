package model;


class Tabuleiro {

    // Inicializando instancias dos paises
    public static Pais AfricaSul = new Pais(Cartas.Territorio.allTerritorios[0].getPais(), Cartas.Territorio.allTerritorios[0].getFronteira());
    public static Pais Angola = new Pais(Cartas.Territorio.allTerritorios[1].getPais(), Cartas.Territorio.allTerritorios[1].getFronteira());
    public static Pais Argelia = new Pais(Cartas.Territorio.allTerritorios[2].getPais(), Cartas.Territorio.allTerritorios[2].getFronteira());
    public static Pais Egito = new Pais(Cartas.Territorio.allTerritorios[3].getPais(), Cartas.Territorio.allTerritorios[3].getFronteira());
    public static Pais Nigeria = new Pais(Cartas.Territorio.allTerritorios[4].getPais(), Cartas.Territorio.allTerritorios[4].getFronteira());
    public static Pais Somalia = new Pais(Cartas.Territorio.allTerritorios[5].getPais(), Cartas.Territorio.allTerritorios[5].getFronteira());

    public static Pais Alasca = new Pais(Cartas.Territorio.allTerritorios[6].getPais(), Cartas.Territorio.allTerritorios[6].getFronteira());
    public static Pais Calgary = new Pais(Cartas.Territorio.allTerritorios[7].getPais(), Cartas.Territorio.allTerritorios[7].getFronteira());
    public static Pais California = new Pais(Cartas.Territorio.allTerritorios[8].getPais(), Cartas.Territorio.allTerritorios[8].getFronteira());
    public static Pais Groelandia = new Pais(Cartas.Territorio.allTerritorios[9].getPais(), Cartas.Territorio.allTerritorios[9].getFronteira());
    public static Pais NovaYork = new Pais(Cartas.Territorio.allTerritorios[10].getPais(), Cartas.Territorio.allTerritorios[10].getFronteira());
    public static Pais Mexico = new Pais(Cartas.Territorio.allTerritorios[11].getPais(), Cartas.Territorio.allTerritorios[11].getFronteira());
    public static Pais Quebec = new Pais(Cartas.Territorio.allTerritorios[12].getPais(), Cartas.Territorio.allTerritorios[12].getFronteira());
    public static Pais Texas = new Pais(Cartas.Territorio.allTerritorios[13].getPais(), Cartas.Territorio.allTerritorios[13].getFronteira());
    public static Pais Vancouver = new Pais(Cartas.Territorio.allTerritorios[14].getPais(), Cartas.Territorio.allTerritorios[14].getFronteira());

    public static Pais ArabiaSaudita = new Pais(Cartas.Territorio.allTerritorios[15].getPais(), Cartas.Territorio.allTerritorios[15].getFronteira());
    public static Pais Bangladesh = new Pais(Cartas.Territorio.allTerritorios[16].getPais(), Cartas.Territorio.allTerritorios[16].getFronteira());
    public static Pais Cazaquistao = new Pais(Cartas.Territorio.allTerritorios[17].getPais(), Cartas.Territorio.allTerritorios[17].getFronteira());
    public static Pais China = new Pais(Cartas.Territorio.allTerritorios[18].getPais(), Cartas.Territorio.allTerritorios[18].getFronteira());
    public static Pais CoreiadoNorte = new Pais(Cartas.Territorio.allTerritorios[19].getPais(), Cartas.Territorio.allTerritorios[19].getFronteira());
    public static Pais CoreiadoSul = new Pais(Cartas.Territorio.allTerritorios[20].getPais(), Cartas.Territorio.allTerritorios[20].getFronteira());
    public static Pais Estonia = new Pais(Cartas.Territorio.allTerritorios[21].getPais(), Cartas.Territorio.allTerritorios[21].getFronteira());
    public static Pais India = new Pais(Cartas.Territorio.allTerritorios[22].getPais(), Cartas.Territorio.allTerritorios[22].getFronteira());
    public static Pais Ira = new Pais(Cartas.Territorio.allTerritorios[23].getPais(), Cartas.Territorio.allTerritorios[23].getFronteira());
    public static Pais Iraque = new Pais(Cartas.Territorio.allTerritorios[24].getPais(), Cartas.Territorio.allTerritorios[24].getFronteira());
    public static Pais Japao = new Pais(Cartas.Territorio.allTerritorios[25].getPais(), Cartas.Territorio.allTerritorios[25].getFronteira());
    public static Pais Jordania = new Pais(Cartas.Territorio.allTerritorios[26].getPais(), Cartas.Territorio.allTerritorios[26].getFronteira());
    public static Pais Letonia = new Pais(Cartas.Territorio.allTerritorios[27].getPais(), Cartas.Territorio.allTerritorios[27].getFronteira());
    public static Pais Mongolia = new Pais(Cartas.Territorio.allTerritorios[28].getPais(), Cartas.Territorio.allTerritorios[28].getFronteira());
    public static Pais Paquistao = new Pais(Cartas.Territorio.allTerritorios[29].getPais(), Cartas.Territorio.allTerritorios[29].getFronteira());
    public static Pais Russia = new Pais(Cartas.Territorio.allTerritorios[30].getPais(), Cartas.Territorio.allTerritorios[30].getFronteira());
    public static Pais Siberia = new Pais(Cartas.Territorio.allTerritorios[31].getPais(), Cartas.Territorio.allTerritorios[31].getFronteira());
    public static Pais Siria = new Pais(Cartas.Territorio.allTerritorios[32].getPais(), Cartas.Territorio.allTerritorios[32].getFronteira());
    public static Pais Tailandia = new Pais(Cartas.Territorio.allTerritorios[33].getPais(), Cartas.Territorio.allTerritorios[33].getFronteira());
    public static Pais Turquia = new Pais(Cartas.Territorio.allTerritorios[34].getPais(), Cartas.Territorio.allTerritorios[34].getFronteira());

    public static Pais Argentina = new Pais(Cartas.Territorio.allTerritorios[35].getPais(), Cartas.Territorio.allTerritorios[35].getFronteira());
    public static Pais Brasil = new Pais(Cartas.Territorio.allTerritorios[36].getPais(), Cartas.Territorio.allTerritorios[36].getFronteira());
    public static Pais Peru = new Pais(Cartas.Territorio.allTerritorios[37].getPais(), Cartas.Territorio.allTerritorios[37].getFronteira());
    public static Pais Venezuela = new Pais(Cartas.Territorio.allTerritorios[38].getPais(), Cartas.Territorio.allTerritorios[38].getFronteira());

    public static Pais Espanha = new Pais(Cartas.Territorio.allTerritorios[39].getPais(), Cartas.Territorio.allTerritorios[39].getFronteira());
    public static Pais Franca = new Pais(Cartas.Territorio.allTerritorios[40].getPais(), Cartas.Territorio.allTerritorios[40].getFronteira());
    public static Pais Italia = new Pais(Cartas.Territorio.allTerritorios[41].getPais(), Cartas.Territorio.allTerritorios[41].getFronteira());
    public static Pais Polonia = new Pais(Cartas.Territorio.allTerritorios[42].getPais(), Cartas.Territorio.allTerritorios[42].getFronteira());
    public static Pais ReinoUnido = new Pais(Cartas.Territorio.allTerritorios[43].getPais(), Cartas.Territorio.allTerritorios[43].getFronteira());
    public static Pais Romenia = new Pais(Cartas.Territorio.allTerritorios[44].getPais(), Cartas.Territorio.allTerritorios[44].getFronteira());
    public static Pais Suecia = new Pais(Cartas.Territorio.allTerritorios[45].getPais(), Cartas.Territorio.allTerritorios[45].getFronteira());
    public static Pais Ucrania = new Pais(Cartas.Territorio.allTerritorios[46].getPais(), Cartas.Territorio.allTerritorios[46].getFronteira());

    public static Pais Australia = new Pais(Cartas.Territorio.allTerritorios[47].getPais(), Cartas.Territorio.allTerritorios[47].getFronteira());
    public static Pais Indonesia = new Pais(Cartas.Territorio.allTerritorios[48].getPais(), Cartas.Territorio.allTerritorios[48].getFronteira());
    public static Pais NovaZelandia = new Pais(Cartas.Territorio.allTerritorios[49].getPais(), Cartas.Territorio.allTerritorios[49].getFronteira());
    public static Pais Perth = new Pais(Cartas.Territorio.allTerritorios[50].getPais(), Cartas.Territorio.allTerritorios[50].getFronteira());

    // Continentes
    public static Pais[] Africa = {AfricaSul, Angola, Argelia, Egito, Nigeria, Somalia};
    public static Pais[] AmericaNorte = {Alasca, Calgary, California, Groelandia, NovaYork, Mexico, Quebec, Texas, Vancouver};
    public static Pais[] Asia = {ArabiaSaudita, Bangladesh, Cazaquistao, China, CoreiadoNorte, CoreiadoSul, Estonia, India, Ira,
            Iraque, Japao, Jordania, Letonia, Mongolia, Paquistao, Russia, Siberia, Siria, Tailandia, Turquia};
    public static Pais[] AmericaSul = {Argentina, Brasil, Peru, Venezuela};
    public static Pais[] Europa = {Espanha, Franca, Italia, Polonia, ReinoUnido, Romenia, Suecia, Ucrania};
    public static Pais[] Oceania = {Australia, Indonesia, NovaZelandia, Perth};
    public static Pais[] Teste = {Australia};


}
