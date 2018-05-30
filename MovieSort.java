
public class MovieSort {
    /*
     * Complete the function below.
     */
    static String URLString = "https://jsonmock.hackerrank.com/api/movies/search/?Title=%s&page=%d";
    static GsonBuilder gsonBuilder = new GsonBuilder();
    
    class Movie implements java.io.Serializable{
        String page;
        Integer per_page;
        Integer total;
        Integer total_pages;
        Data[] data;
        class Data{
            String Poster;
            String Title;
            String Type;
            Integer Year;
            String imdbID;
        }
    }
    
    static String[] getMovieTitles(String substr) {
        StringBuffer sb = new StringBuffer();
        Gson gson = gsonBuilder.create();
        Integer total_pages = 0;
        int pageNumber = 1;
        List<String> movieList = new ArrayList<>();
        do{
            try{
                URL url = new URL(String.format(URLString,substr,pageNumber));
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                int responseCode = con.getResponseCode();
                Movie movie = gson.fromJson(new InputStreamReader(con.getInputStream()), Movie.class);
                total_pages = movie.total_pages;
                for(int i = 0; i<movie.data.length; i++){
                    movieList.add(movie.data[i].Title);
                }
                pageNumber++;
            }catch(Exception ex){
            ex.printStackTrace();      
            }
        }while(pageNumber<=total_pages);
        Collections.sort(movieList);
        String[] movies = new String[movieList.size()];
        return movieList.toArray(movies);
    }
