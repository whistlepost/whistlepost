div(id: 'disqus_thread')
script {
    yieldUnescaped """(function() { // DON'T EDIT BELOW THIS LINE
                    var d = document, s = d.createElement('script');

                    s.src = '//${resource.valueMap["disqusShortName"]}.disqus.com/embed.js';

                    s.setAttribute('data-timestamp', +new Date());
                    (d.head || d.body).appendChild(s);
                  })();"""
}
noscript {
    yieldUnescaped 'Please enable JavaScript to view the <a href="https://disqus.com/?ref_noscript" rel="nofollow">comments powered by Disqus.</a>'
}
