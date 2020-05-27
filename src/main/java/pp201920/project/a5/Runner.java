package pp201920.project.a5;

public class Runner {
    
    public static void main(String[] args) {
        
        Response response = new Response(10);

        for(int i = 1; i <= 10; i++){
            String s = "https://tourism.opendatahub.bz.it/api/Activity?pagenumber="+i+"&pagesize=1000";
            MyRequest request = new MyRequest(s,response);
            Thread a = new Thread(request);
            a.start();
        }

        while(Thread.activeCount() > 1){
            // System.out.printf("Currently there are %d results.\n", response.getResults().size());
        }

        System.out.printf("Currently there are %d results.\n", response.getResults().size());

    }

}