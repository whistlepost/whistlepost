out.write """
<div class="block-list">
    <ul class="wnews-list-nostyle">
"""

def rssFeed = currentNode.getProperty('feedUrl').string.toURL().text
def limit = request.requestPathInfo.suffix ? request.requestPathInfo.suffix.substring(1) as int : Integer.MAX_VALUE;

def rss = new XmlSlurper().parseText(rssFeed)
rss.channel.item.list().take(limit).each {
    out.write("<li><a href=\"$it.link\" title=\"${it.description.text()}\">$it.title</a></li>")
}
if (currentNode.hasProperty('htmlUrl')) {
    out.write("<li><a href=\"${currentNode.getProperty('htmlUrl').string}\">More..</a></li>")
}

out.write """
    </ul>
</div>
"""