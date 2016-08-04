out.write """
<div class="block-list">
    <ul class="wnews-list-nostyle">
"""

def rssFeed = currentNode.getProperty('feedUrl').string.toURL().text

def rss = new XmlSlurper().parseText(rssFeed)
rss.channel.item.each{
    out.write("<li><a href=\"$it.link\">$it.title</a></li>")
}

out.write """
    </ul>
</div>
"""