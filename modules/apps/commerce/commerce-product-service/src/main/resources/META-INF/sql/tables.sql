create table CPAttachmentFileEntry (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	externalReferenceCode VARCHAR(75) null,
	CPAttachmentFileEntryId LONG not null,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	fileEntryId LONG,
	cdnEnabled BOOLEAN,
	cdnURL STRING null,
	displayDate DATE null,
	expirationDate DATE null,
	title STRING null,
	json TEXT null,
	priority DOUBLE,
	type_ INTEGER,
	lastPublishDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	primary key (CPAttachmentFileEntryId, ctCollectionId)
);

create table CPDSpecificationOptionValue (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	CPDSpecificationOptionValueId LONG not null,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	CPDefinitionId LONG,
	CPSpecificationOptionId LONG,
	CPOptionCategoryId LONG,
	value STRING null,
	priority DOUBLE,
	lastPublishDate DATE null,
	primary key (CPDSpecificationOptionValueId, ctCollectionId)
);

create table CPDefinition (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	defaultLanguageId VARCHAR(75) null,
	CPDefinitionId LONG not null,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	CProductId LONG,
	CPTaxCategoryId LONG,
	productTypeName VARCHAR(75) null,
	availableIndividually BOOLEAN,
	ignoreSKUCombinations BOOLEAN,
	shippable BOOLEAN,
	freeShipping BOOLEAN,
	shipSeparately BOOLEAN,
	shippingExtraPrice DOUBLE,
	width DOUBLE,
	height DOUBLE,
	depth DOUBLE,
	weight DOUBLE,
	taxExempt BOOLEAN,
	telcoOrElectronics BOOLEAN,
	DDMStructureKey VARCHAR(75) null,
	published BOOLEAN,
	displayDate DATE null,
	expirationDate DATE null,
	lastPublishDate DATE null,
	subscriptionEnabled BOOLEAN,
	subscriptionLength INTEGER,
	subscriptionType VARCHAR(75) null,
	subscriptionTypeSettings TEXT null,
	maxSubscriptionCycles LONG,
	deliverySubscriptionEnabled BOOLEAN,
	deliverySubscriptionLength INTEGER,
	deliverySubscriptionType VARCHAR(75) null,
	deliverySubTypeSettings VARCHAR(75) null,
	deliveryMaxSubscriptionCycles LONG,
	accountGroupFilterEnabled BOOLEAN,
	channelFilterEnabled BOOLEAN,
	version INTEGER,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	primary key (CPDefinitionId, ctCollectionId)
);

create table CPDefinitionLink (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	CPDefinitionLinkId LONG not null,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	CPDefinitionId LONG,
	CProductId LONG,
	priority DOUBLE,
	type_ VARCHAR(75) null,
	primary key (CPDefinitionLinkId, ctCollectionId)
);

create table CPDefinitionLocalization (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	cpDefinitionLocalizationId LONG not null,
	companyId LONG,
	CPDefinitionId LONG,
	languageId VARCHAR(75) null,
	name STRING null,
	shortDescription STRING null,
	description TEXT null,
	metaTitle VARCHAR(255) null,
	metaDescription VARCHAR(255) null,
	metaKeywords VARCHAR(255) null,
	primary key (cpDefinitionLocalizationId, ctCollectionId)
);

create table CPDefinitionOptionRel (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	CPDefinitionOptionRelId LONG not null,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	CPDefinitionId LONG,
	CPOptionId LONG,
	name STRING null,
	description STRING null,
	DDMFormFieldTypeName VARCHAR(75) null,
	priority DOUBLE,
	facetable BOOLEAN,
	required BOOLEAN,
	skuContributor BOOLEAN,
	key_ VARCHAR(75) null,
	priceType VARCHAR(75) null,
	primary key (CPDefinitionOptionRelId, ctCollectionId)
);

create table CPDefinitionOptionValueRel (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	CPDefinitionOptionValueRelId LONG not null,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	CPDefinitionOptionRelId LONG,
	CPInstanceUuid VARCHAR(75) null,
	CProductId LONG,
	name STRING null,
	priority DOUBLE,
	key_ VARCHAR(75) null,
	quantity INTEGER,
	preselected BOOLEAN,
	price DECIMAL(30, 16) null,
	primary key (CPDefinitionOptionValueRelId, ctCollectionId)
);

create table CPDisplayLayout (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	CPDisplayLayoutId LONG not null,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	layoutUuid VARCHAR(75) null,
	primary key (CPDisplayLayoutId, ctCollectionId)
);

create table CPInstance (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	externalReferenceCode VARCHAR(75) null,
	CPInstanceId LONG not null,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	CPDefinitionId LONG,
	CPInstanceUuid VARCHAR(75) null,
	sku VARCHAR(75) null,
	gtin VARCHAR(75) null,
	manufacturerPartNumber VARCHAR(75) null,
	purchasable BOOLEAN,
	width DOUBLE,
	height DOUBLE,
	depth DOUBLE,
	weight DOUBLE,
	price DECIMAL(30, 16) null,
	promoPrice DECIMAL(30, 16) null,
	cost DECIMAL(30, 16) null,
	published BOOLEAN,
	displayDate DATE null,
	expirationDate DATE null,
	lastPublishDate DATE null,
	overrideSubscriptionInfo BOOLEAN,
	subscriptionEnabled BOOLEAN,
	subscriptionLength INTEGER,
	subscriptionType VARCHAR(75) null,
	subscriptionTypeSettings TEXT null,
	maxSubscriptionCycles LONG,
	deliverySubscriptionEnabled BOOLEAN,
	deliverySubscriptionLength INTEGER,
	deliverySubscriptionType VARCHAR(75) null,
	deliverySubTypeSettings VARCHAR(75) null,
	deliveryMaxSubscriptionCycles LONG,
	unspsc VARCHAR(75) null,
	discontinued BOOLEAN,
	discontinuedDate DATE null,
	replacementCPInstanceUuid VARCHAR(75) null,
	replacementCProductId LONG,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	primary key (CPInstanceId, ctCollectionId)
);

create table CPInstanceOptionValueRel (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	CPInstanceOptionValueRelId LONG not null,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	CPDefinitionOptionRelId LONG,
	CPDefinitionOptionValueRelId LONG,
	CPInstanceId LONG,
	primary key (CPInstanceOptionValueRelId, ctCollectionId)
);

create table CPMeasurementUnit (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	CPMeasurementUnitId LONG not null,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name STRING null,
	key_ VARCHAR(75) null,
	rate DOUBLE,
	primary_ BOOLEAN,
	priority DOUBLE,
	type_ INTEGER,
	lastPublishDate DATE null,
	primary key (CPMeasurementUnitId, ctCollectionId)
);

create table CPOption (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	externalReferenceCode VARCHAR(75) null,
	CPOptionId LONG not null,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name STRING null,
	description STRING null,
	DDMFormFieldTypeName VARCHAR(75) null,
	facetable BOOLEAN,
	required BOOLEAN,
	skuContributor BOOLEAN,
	key_ VARCHAR(75) null,
	lastPublishDate DATE null,
	primary key (CPOptionId, ctCollectionId)
);

create table CPOptionCategory (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	CPOptionCategoryId LONG not null,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	title STRING null,
	description STRING null,
	priority DOUBLE,
	key_ VARCHAR(75) null,
	lastPublishDate DATE null,
	primary key (CPOptionCategoryId, ctCollectionId)
);

create table CPOptionValue (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	externalReferenceCode VARCHAR(75) null,
	CPOptionValueId LONG not null,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	CPOptionId LONG,
	name STRING null,
	priority DOUBLE,
	key_ VARCHAR(75) null,
	lastPublishDate DATE null,
	primary key (CPOptionValueId, ctCollectionId)
);

create table CPSpecificationOption (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	CPSpecificationOptionId LONG not null,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	CPOptionCategoryId LONG,
	title STRING null,
	description STRING null,
	facetable BOOLEAN,
	key_ VARCHAR(75) null,
	lastPublishDate DATE null,
	primary key (CPSpecificationOptionId, ctCollectionId)
);

create table CPTaxCategory (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	externalReferenceCode VARCHAR(75) null,
	CPTaxCategoryId LONG not null,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name STRING null,
	description STRING null,
	primary key (CPTaxCategoryId, ctCollectionId)
);

create table CProduct (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	externalReferenceCode VARCHAR(75) null,
	CProductId LONG not null,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	publishedCPDefinitionId LONG,
	latestVersion INTEGER,
	primary key (CProductId, ctCollectionId)
);

create table CommerceCatalog (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	externalReferenceCode VARCHAR(75) null,
	commerceCatalogId LONG not null,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	commerceCurrencyCode VARCHAR(75) null,
	catalogDefaultLanguageId VARCHAR(75) null,
	system_ BOOLEAN,
	primary key (commerceCatalogId, ctCollectionId)
);

create table CommerceChannel (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	externalReferenceCode VARCHAR(75) null,
	commerceChannelId LONG not null,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	siteGroupId LONG,
	name VARCHAR(75) null,
	type_ VARCHAR(75) null,
	typeSettings VARCHAR(75) null,
	commerceCurrencyCode VARCHAR(75) null,
	priceDisplayType VARCHAR(75) null,
	discountsTargetNetPrice BOOLEAN,
	primary key (commerceChannelId, ctCollectionId)
);

create table CommerceChannelRel (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	commerceChannelRelId LONG not null,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	commerceChannelId LONG,
	primary key (commerceChannelRelId, ctCollectionId)
);