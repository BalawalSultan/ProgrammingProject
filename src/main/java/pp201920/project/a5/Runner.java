package pp201920.project.a5;

public class Runner {
    
    public static void main(String[] args) {
        
        Response response = new Response(10);

        for(int i = 1; i <= 10; i++){
            String s = "https://tourism.opendatahub.bz.it/api/Activity?pagenumber="+i+"&pagesize=500";
            MyRequest request = new MyRequest(s,response);
            Thread a = new Thread(request);
            a.start();
        }

        while(Thread.activeCount() > 1){}

        ActivityMap map = new ActivityMap();

        for (String json : response.getResults()) {
            ActivityParser parser = new ActivityParser(map,json);
            Thread a = new Thread(parser);
            a.start();
        }

        while(Thread.activeCount() > 1){}

        try {
            Thread.sleep(4000);
        } catch (Exception e) {
            System.err.println("Exception!");
        }

        System.out.printf("The regions with most activities are: ");

        for (String region : map.getRegionsWithMostActivities()) {
            System.out.printf("%s,", region);
        }

        System.out.printf(";\nThe regions with least activities are: ");

        for (String region : map.getRegionsWithLeastActivities()) {
            System.out.printf("%s,", region);
        }

    }

}