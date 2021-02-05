
type TApiMapKey = 'questionnaire' | 'nucleicAcid';

type TActionsApiMap = {
    [key in TApiMapKey]: TApi;
};

type TActionsIsUse = (key: TApiMapKey) => Promise<boolean>;
