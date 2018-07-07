package twitter;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Observable;
import java.util.Observer;

/**
 * Encapsulates the connection to Twitter
 *
 * Terms to include in the returned tweets can be set with setFilterTerms
 *
 * Implements Observable - each received tweet is signalled to all observers
 */
public class LiveTwitterSource extends TwitterSource {
    private TwitterStream twitterStream;
    private StatusListener listener;

    public LiveTwitterSource() {
        initializeTwitterStream();
    }

    protected void sync() {
        FilterQuery filter = new FilterQuery();
        // https://stackoverflow.com/questions/21383345/using-multiple-threads-to-get-data-from-twitter-using-twitter4j
        String[] queriesArray = terms.toArray(new String[0]);
        filter.track(queriesArray);

        System.out.println("Syncing live Twitter stream with " + terms);

        twitterStream.filter(filter);
    }

    private void initializeListener() {
        listener = new StatusAdapter() {
            @Override
            public void onStatus(Status status) {
                // This method is called each time a tweet is delivered by the twitter API
                if (status.getPlace() != null) {
                    handleTweet(status);
                }
           }
        };
    }

    // Create ConfigurationBuilder and pass in necessary credentials to authorize properly, then create TwitterStream.
    private void initializeTwitterStream() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey("XlAD9tN2YtCOpQrUY0zEWbRqW")
                .setOAuthConsumerSecret("owteM7Ze6xjiEAlPwfr4JDkwpK7sgkAfpUBj2xbYN3uKNutnlz")
                .setOAuthAccessToken("996461684855320577-G3YWP5i6JODPLBcXwxI9G03ZCfg5LeT")
                .setOAuthAccessTokenSecret("GRIR2hi3QDmV3I5ATxSH0zRUnbj7kKjaWbYH8m3XGDf0N");

        // Pass the ConfigurationBuilder in when constructing TwitterStreamFactory.
        twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        initializeListener();
        twitterStream.addListener(listener);
    }
}
