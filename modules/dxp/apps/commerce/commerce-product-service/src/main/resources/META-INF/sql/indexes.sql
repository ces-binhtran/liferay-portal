create index IX_A6E0353A on CPAttachmentFileEntry (classNameId, classPK, type_, status);
create index IX_A0B4C71A on CPAttachmentFileEntry (displayDate, status);
create index IX_C2C5D600 on CPAttachmentFileEntry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_BFCBDC82 on CPAttachmentFileEntry (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_217AF702 on CPDefinition (companyId);
create index IX_A465D100 on CPDefinition (displayDate, status);
create index IX_419350EA on CPDefinition (groupId, status);
create index IX_8EA585DA on CPDefinition (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_BA9BADC on CPDefinition (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_EA724334 on CPDefinitionLink (CPDefinitionId1, CPDefinitionId2, type_);
create index IX_E8875D27 on CPDefinitionLink (CPDefinitionId2);
create index IX_220CC8F4 on CPDefinitionLink (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_865AFC76 on CPDefinitionLink (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_F4B4ACB5 on CPDefinitionLocalization (CPDefinitionId, languageId[$COLUMN_LENGTH:75$]);

create index IX_749E99EB on CPDefinitionOptionRel (CPDefinitionId, skuContributor);
create index IX_449BFCFE on CPDefinitionOptionRel (companyId);
create index IX_A65BAB00 on CPDefinitionOptionRel (groupId);
create index IX_7BED0C5E on CPDefinitionOptionRel (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_EB691260 on CPDefinitionOptionRel (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_8FDF08C0 on CPDefinitionOptionValueRel (CPDefinitionOptionRelId, key_[$COLUMN_LENGTH:75$]);
create index IX_44C2E505 on CPDefinitionOptionValueRel (companyId);
create index IX_695AE8C7 on CPDefinitionOptionValueRel (groupId);
create index IX_CD95E77 on CPDefinitionOptionValueRel (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_34516B9 on CPDefinitionOptionValueRel (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_BCDDC9C8 on CPDefinitionSpecificationOptionValue (CPDefinitionId, CPSpecificationOptionId);
create index IX_5B2C0E on CPDefinitionSpecificationOptionValue (CPOptionCategoryId);
create index IX_A48E6A77 on CPDefinitionSpecificationOptionValue (CPSpecificationOptionId);
create index IX_BF07474B on CPDefinitionSpecificationOptionValue (groupId);
create index IX_46151473 on CPDefinitionSpecificationOptionValue (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_72AE1BB5 on CPDefinitionSpecificationOptionValue (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_290BF7BA on CPDisplayLayout (classNameId, classPK);
create index IX_EEFA81D9 on CPDisplayLayout (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_68BBAA9B on CPDisplayLayout (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_71874AE8 on CPFriendlyURLEntry (groupId, companyId, classNameId, classPK, languageId[$COLUMN_LENGTH:75$], main);
create index IX_393BFCD8 on CPFriendlyURLEntry (groupId, companyId, classNameId, classPK, languageId[$COLUMN_LENGTH:75$], urlTitle[$COLUMN_LENGTH:255$]);
create index IX_C13D69E1 on CPFriendlyURLEntry (groupId, companyId, classNameId, classPK, main);
create index IX_3E26073 on CPFriendlyURLEntry (groupId, companyId, classNameId, languageId[$COLUMN_LENGTH:75$], urlTitle[$COLUMN_LENGTH:255$]);
create index IX_C7AAAB6C on CPFriendlyURLEntry (groupId, companyId, classNameId, urlTitle[$COLUMN_LENGTH:255$]);
create unique index IX_4BAE95CD on CPFriendlyURLEntry (groupId, languageId[$COLUMN_LENGTH:75$], urlTitle[$COLUMN_LENGTH:255$]);
create index IX_BD972D55 on CPFriendlyURLEntry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F4450517 on CPFriendlyURLEntry (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_7E830576 on CPInstance (CPDefinitionId, sku[$COLUMN_LENGTH:75$]);
create index IX_F4C9CDD on CPInstance (CPDefinitionId, status);
create index IX_48C70BC0 on CPInstance (companyId);
create index IX_7C65903E on CPInstance (displayDate, status);
create index IX_C1F8242 on CPInstance (groupId);
create index IX_8A7A3F5C on CPInstance (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F902ECDE on CPInstance (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_5DD6FB76 on CPOption (groupId, key_[$COLUMN_LENGTH:75$]);
create index IX_C565E27C on CPOption (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_BABCD7FE on CPOption (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_88DFEE42 on CPOptionCategory (companyId);
create unique index IX_120A9C18 on CPOptionCategory (groupId, key_[$COLUMN_LENGTH:75$]);
create index IX_957E69A on CPOptionCategory (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_8CFD4B9C on CPOptionCategory (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_F4C57C5A on CPOptionValue (CPOptionId, key_[$COLUMN_LENGTH:75$]);
create index IX_C95EFDB3 on CPOptionValue (companyId);
create index IX_F5E154F5 on CPOptionValue (groupId);
create index IX_17FEC609 on CPOptionValue (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_1D633ACB on CPOptionValue (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_421ED80 on CPSpecificationOption (CPOptionCategoryId);
create unique index IX_1E01842D on CPSpecificationOption (groupId, key_[$COLUMN_LENGTH:75$]);
create index IX_5B218A65 on CPSpecificationOption (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_FFE36627 on CPSpecificationOption (uuid_[$COLUMN_LENGTH:75$], groupId);