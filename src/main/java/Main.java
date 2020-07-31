import encapsulaciones.Estudiante;
import kong.unirest.GenericType;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        /**
         * Todos los estudiantes
         */
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Todos los estudiantes:");
        List<Estudiante> listaEstudiante = Unirest.get("http://localhost:7000/api/estudiante/").
                                           asObject(new GenericType<List<Estudiante>>() {
                                           }).getBody();
        listaEstudiante.forEach(estudiante -> {
           System.out.println(estudiante.getNombre());
        });
        System.out.println("---------------------------------------------------------------------");

        /**
         * Consultar Estudiante
         */
        System.out.println("---------------------------------------------------------------------");
        String matricula = "20160338";
        System.out.println("Consultado matricula:"+matricula+"\n");

        Estudiante estudiante = Unirest.get("http://localhost:7000/api/estudiante/{matricula}").
                routeParam("matricula",matricula).
                asObject(Estudiante.class).getBody();
        try{
            System.out.println("Resultado: "+estudiante.getNombre());
        }catch (Exception e){
            System.out.println("No existe el estudiante.");
        }
        System.out.println("---------------------------------------------------------------------");

        /**
         * Nuevo Estudiante
         */
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Creando nuevo estudiante . . . ");
        HttpResponse<JsonNode> response = Unirest.post("http://localhost:7000/api/estudiante/").
                                          header("Content-Type","application/json")
                                          .body("{\"matricula\":20160277, \"nombre\": \"Jesus Rosario\", \"carrera\": \"ITT\"}")
                                          .asJson();
        System.out.println("---------------------------------------------------------------------");

        /**
         * Consulta al estudiante creado:
         */
        System.out.println("---------------------------------------------------------------------");
        String matricula2 = "20160277";
        System.out.println("Consultado matricula:"+matricula2+"\n");

        Estudiante estudiante2 = Unirest.get("http://localhost:7000/api/estudiante/{matricula}").
                routeParam("matricula",matricula2).
                asObject(Estudiante.class).getBody();

        try{
            System.out.println("Resultado: "+estudiante2.getNombre());
        }catch (Exception e){
            System.out.println("No existe el estudiante.");
        }
        System.out.println("---------------------------------------------------------------------");
        String matriculaDelete = "20160277";
        System.out.println("Borrando matricula:"+matriculaDelete+"\n");

        HttpResponse delete = Unirest.delete("http://localhost:7000/api/estudiante/{matricula}").
                routeParam("matricula",matriculaDelete)
                .asEmpty();

        System.out.println("Resultado: "+delete.getStatus());
        System.out.println("---------------------------------------------------------------------");
    }

}
