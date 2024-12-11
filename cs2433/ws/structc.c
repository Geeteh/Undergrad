union <nameofunion> {
	float fv;
	float lv;
};

struct <nameofstruct> {
	int discriminator;
	union whatever u;

};
