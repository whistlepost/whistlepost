FROM apache/sling:11
LABEL maintainer="fortuna@micronode.com"

HEALTHCHECK CMD wget -O- localhost:8080/system/health.txt?httpStatus=CRITICAL:503
