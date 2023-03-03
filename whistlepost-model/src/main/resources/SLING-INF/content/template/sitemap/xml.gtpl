xmlDeclaration()

urlset(xmlns: "http://www.sitemaps.org/schemas/sitemap/0.9",
    'xmlns:xsi': "http://www.w3.org/2001/XMLSchema-instance",
    'xsi:schemaLocation': "http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd") {

    def sitemap = resource.adaptTo(org.whistlepost.model.Sitemap)
    sitemap.uris.each { uri ->
        url {
            loc("${sitemap.config.urlBase()}$uri")
            changefreq(sitemap.config.changeFrequency())
        }
    }
}
