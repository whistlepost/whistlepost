def robots = resource.adaptTo(org.whistlepost.model.Robots)
out.write "User-agent: ${robots.userAgent}\n"
robots.allow.each {
  out.write "Allow: ${it}\n"
}
out.write "Sitemap: ${robots.sitemap}\n"
