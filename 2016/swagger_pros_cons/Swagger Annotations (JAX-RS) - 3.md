```java
   private final String API_NOTES =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
            + "Proin porta ex orci, non elementum augue aliquet sed. "
            + "Quisque rhoncus tincidunt tortor eu dapibus. Cras aliquam augue imperdiet nibh sagittis molestie. " +
            "Proin risus tortor, sodales sit amet blandit ac, vehicula vitae justo.\n\n" +
            "## Proin risus tortor\n\n" +
            "Lorem _ipsum_ **dolor** sit amet, consectetur adipiscing elit.\n\n" +
            "> Some cool quote\n\n" +
            "|Sit amet | Lorem dolor | Cosectetur elit|\n" +
            "|-------|------|------|\n" +
            "|`ipsum` | augue | SAGGITTIS |\n" +
            "|`dolor` | augue | SAGGITTIS |\n" +
            "|`amet+adipiscing`| augue | SAGGITTIS |\n" +
            "|`imperdiet` | augue | SAGGITTIS  |\n" +
            "|`dapibus` | augue | SAGGITTIS |\n" +
            "|`aliquam` | augue | SAGGITTIS |\n" +
            "|`nibh` | augue | SAGGITTIS |\n" +
            "|`risus` | augue | SAGGITTIS |\n" +
            "|`blandit` | augue | — |\n" +
            "|`nibh` | tortor | — |";

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Get data",
            notes = API_NOTES)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = AstronomyDataByGeoCodeModel.class, responseContainer = "List"),
            @ApiResponse(code = 204, message = "No Data Available - empty array"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized - API operation not allowed for this api key"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 501, message = "Not Implemented"),
            @ApiResponse(code = 502, message = "Bad Gateway"),
            @ApiResponse(code = 503, message = "Service Unavailable"),
            @ApiResponse(code = 504, message = "Gateway Timeout"),
    })
    public Response getAstronomyDataByLatLon(
            @Context final Variant responseVariant,

            @ApiParam(value = "The format of the response (json)",
                    required = false, allowableValues = "json, xml",
                    example = "json")
            @QueryParam("format") final String format,

            @ApiParam(value = "Unit of Measure",
                    required = false, allowableValues = "e, m, h, s",
                    example = "m")
            @QueryParam("units") final UnitsOfMeasureType unitsOfMeasure,

            @ApiParam(value = "Date parameter is used to call a single day of historical data.\n" +
                    "Dates shall use ISO 8601 standards",
                    required = true,
                    example = "20160420")
            @QueryParam("date") final String date,

            @ApiParam(value = "Days parameter is used for incrementation of data usually in conjunction with dates or using current system date.",
                    example = "2",
                    required = true)
            @QueryParam("days") final Integer numDays,

            @ApiParam(value = "Comp uses valid latitudes and longitude coordinates to identify locations worldwide.  Uses WGS84 geocode coordinate reference system. https://www.w3.org/2003/01/geo/\n" +
                    "33.40,83.19 is the manner in which a latitude and longitude is accepted for the weather APIs.",
                    required = true,
                    example = "34.063,-84.217")
            @QueryParam("geocode") final GeoCode geocode) {

      ...

        return buildResponseOk(responseVariant, result);
    }

```