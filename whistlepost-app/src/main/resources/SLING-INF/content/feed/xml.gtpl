xmlDeclaration()

feed(xmlns: "http://www.w3.org/2005/Atom") {

    def feed = resource.adaptTo(org.whistlepost.model.Feed)
    title(feed.title)
    link(feed.link)
    updated(feed.updated)
    author {
        name(feed.author)
    }
    id(feed.id)
    feed.entries.each { entry ->
        entry {
            title(entry.title)
            link(entry.link)
            id(entry.id)
            updated(entry.updated)
            summary(entry.summary)
        }
    }
}
