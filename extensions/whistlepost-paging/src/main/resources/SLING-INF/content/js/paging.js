function calculateOffset(pageVal, data) {
    data.offset = 0;
	data.limit = Number.MAX_SAFE_INTEGER;

	if (pageVal) {
		var range = pageVal.substring(1).split("-")
		if (range.length > 1) {
			data.offset = Math.min((parseInt(range[0]) - 1) * data.pageSize, data.total - 1);
			data.limit = parseInt(range[1]);
		} else {
			data.offset = Math.min((parseInt(range[0]) - 1) * data.pageSize, data.total - 1);
		}
	}
}

function calculatePageCount(data) {
	data.pageCount = Math.ceil(parseFloat(data.total) / data.pageSize);
}
