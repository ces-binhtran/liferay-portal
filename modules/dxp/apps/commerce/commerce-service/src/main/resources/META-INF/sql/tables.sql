create table CommerceCart (
	uuid_ VARCHAR(75) null,
	commerceCartId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	type_ INTEGER
);

create table CommerceCartItem (
	commerceCartItemId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceCartId LONG,
	CPDefinitionId LONG,
	CPInstanceId LONG,
	quantity INTEGER,
	json VARCHAR(75) null
);

create table CommerceCountry (
	uuid_ VARCHAR(75) null,
	commerceCountryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name STRING null,
	billingAllowed BOOLEAN,
	shippingAllowed BOOLEAN,
	twoLettersISOCode VARCHAR(2) null,
	threeLettersISOCode VARCHAR(3) null,
	numericISOCode INTEGER,
	subjectToVAT BOOLEAN,
	priority DOUBLE,
	active_ BOOLEAN,
	lastPublishDate DATE null
);

create table CommerceOrder (
	commerceOrderId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	orderUserId LONG,
	total DOUBLE,
	status INTEGER
);

create table CommerceOrderItem (
	commerceOrderItemId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceOrderId LONG,
	CPDefinitionId LONG,
	CPInstanceId LONG,
	quantity INTEGER,
	json VARCHAR(75) null,
	title STRING null,
	sku VARCHAR(75) null,
	price DOUBLE
);

create table CommercePaymentMethod (
	commercePaymentMethodId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name STRING null,
	description STRING null,
	engineKey VARCHAR(75) null,
	priority DOUBLE,
	active_ BOOLEAN
);

create table CommerceRegion (
	uuid_ VARCHAR(75) null,
	commerceRegionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceCountryId LONG,
	name VARCHAR(75) null,
	code_ VARCHAR(75) null,
	priority DOUBLE,
	active_ BOOLEAN,
	lastPublishDate DATE null
);

create table CommerceShippingMethod (
	commerceShippingMethodId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name STRING null,
	description STRING null,
	engineKey VARCHAR(75) null,
	priority DOUBLE,
	active_ BOOLEAN
);

create table CommerceWarehouse (
	commerceWarehouseId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	description STRING null,
	street1 VARCHAR(75) null,
	street2 VARCHAR(75) null,
	street3 VARCHAR(75) null,
	city VARCHAR(75) null,
	zip VARCHAR(75) null,
	commerceRegionId LONG,
	commerceCountryId LONG
);

create table CommerceWarehouseItem (
	commerceWarehouseItemId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceWarehouseId LONG,
	classNameId LONG,
	classPK LONG,
	quantity INTEGER
);