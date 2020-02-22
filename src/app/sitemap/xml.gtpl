xmlDeclaration()

urlset(xmlns: "http://www.sitemaps.org/schemas/sitemap/0.9",
    'xmlns:xsi': "http://www.w3.org/2001/XMLSchema-instance",
    'xsi:schemaLocation': "http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd") {

    def urlBase = resource.valueMap["url-base"]
    def change_frequency = resource.valueMap["change-frequency"]
    def uris = resource.valueMap['uris']

    uris.each { uri ->
        url {
            loc("$urlBase$uri")
            changefreq(change_frequency)
        }
    }
}
